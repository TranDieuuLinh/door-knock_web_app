"use client";

import { useEffect, useMemo, useRef, useState } from "react";
import { useUsers } from "@/hooks/useUsers";
import { Button } from "@/components/ui/Button";
import type { SortOrder, UserWithVisitStats, VolunteerRole } from "@/types";

const SECTION_ROLES: VolunteerRole[] = ["DOORKNOCKER", "COORDINATOR", "ADMIN"];
const PAGE_SIZES = [5, 10, 20, 50] as const;

type ActivityStatus = "active" | "on_leave";

const ALL_ACTIVITY_STATUSES: ActivityStatus[] = ["active", "on_leave"];

const ACTIVITY_STATUS_LABELS: Record<ActivityStatus, string> = {
  active: "Active",
  on_leave: "On Leave",
};

function createDefaultStatusSelection() {
  return new Set<ActivityStatus>(ALL_ACTIVITY_STATUSES);
}

function isUserActive(user: UserWithVisitStats) {
  return Boolean(user.lastActive);
}

function getUserActivityStatus(user: UserWithVisitStats): ActivityStatus {
  return isUserActive(user) ? "active" : "on_leave";
}

function matchesStatusSelection(
  user: UserWithVisitStats,
  selectedStatuses: Set<ActivityStatus>,
) {
  if (
    selectedStatuses.size === 0 ||
    selectedStatuses.size === ALL_ACTIVITY_STATUSES.length
  ) {
    return true;
  }
  return selectedStatuses.has(getUserActivityStatus(user));
}

function StatusFilterDropdown({
  selectedStatuses,
  onSelectedStatusesChange,
}: {
  selectedStatuses: Set<ActivityStatus>;
  onSelectedStatusesChange: (statuses: Set<ActivityStatus>) => void;
}) {
  const [open, setOpen] = useState(false);
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    function handleClickOutside(event: MouseEvent) {
      if (
        containerRef.current &&
        !containerRef.current.contains(event.target as Node)
      ) {
        setOpen(false);
      }
    }

    if (open) {
      document.addEventListener("mousedown", handleClickOutside);
    }

    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, [open]);

  function toggleStatus(status: ActivityStatus) {
    const next = new Set(selectedStatuses);
    if (next.has(status)) {
      next.delete(status);
    } else {
      next.add(status);
    }
    onSelectedStatusesChange(next);
  }

  return (
    <div ref={containerRef} className="relative">
      <Button
        variant="secondary"
        className="h-9 rounded-full px-4 py-1"
        onClick={() => setOpen((current) => !current)}
        aria-expanded={open}
        aria-haspopup="listbox"
      >
        All Statuses
      </Button>
      {open && (
        <div
          role="listbox"
          aria-label="Filter by status"
          className="absolute right-0 z-10 mt-2 w-44 rounded-md border border-zinc-200 bg-white py-1 shadow-lg"
        >
          {ALL_ACTIVITY_STATUSES.map((status) => (
            <label
              key={status}
              className="flex cursor-pointer items-center gap-2 px-3 py-2 text-sm text-zinc-700 hover:bg-zinc-50"
            >
              <input
                type="checkbox"
                checked={selectedStatuses.has(status)}
                onChange={() => toggleStatus(status)}
                className="h-4 w-4 rounded border-zinc-300"
              />
              {ACTIVITY_STATUS_LABELS[status]}
            </label>
          ))}
        </div>
      )}
    </div>
  );
}

function formatRole(role: VolunteerRole) {
  return role.charAt(0) + role.slice(1).toLowerCase();
}

function formatStatus(user: UserWithVisitStats) {
  return isUserActive(user) ? "Active" : "On Leave";
}

function StatusBadge({ user }: { user: UserWithVisitStats }) {
  const active = isUserActive(user);
  const label = formatStatus(user);

  return (
    <span
      className={`inline-flex rounded-full px-2 py-0.5 text-[11px] font-medium ${
        active
          ? "bg-green-50 text-green-700"
          : "bg-amber-50 text-amber-800"
      }`}
    >
      {label}
    </span>
  );
}

function formatLastActive(lastActive: string | null) {
  if (!lastActive) {
    return "Never";
  }

  const now = Date.now();
  const then = new Date(lastActive).getTime();
  if (Number.isNaN(then)) {
    return "N/A";
  }
  const diffMins = Math.max(1, Math.round((now - then) / 60000));
  if (diffMins < 60) {
    return `${diffMins} mins ago`;
  }
  const diffHours = Math.round(diffMins / 60);
  if (diffHours < 24) {
    return `${diffHours} hours ago`;
  }
  const diffDays = Math.round(diffHours / 24);
  return `${diffDays} days ago`;
}

function SearchIcon() {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      className="h-5 w-5 shrink-0 text-themeColor-500"
      aria-hidden
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth={2}
        d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
      />
    </svg>
  );
}

function SortIndicator({
  active,
  sortOrder,
}: {
  active: boolean;
  sortOrder: SortOrder;
}) {
  if (!active) {
    return <span className="ml-1 text-zinc-300">↕</span>;
  }
  return (
    <span className="ml-1 text-zinc-700">{sortOrder === "asc" ? "↑" : "↓"}</span>
  );
}

function SortableColumnHeader({
  label,
  field,
  sortBy,
  sortOrder,
  onSort,
}: {
  label: string;
  field: string;
  sortBy: string;
  sortOrder: SortOrder;
  onSort: (field: string) => void;
}) {
  return (
    <th className="px-2 py-2">
      <button
        type="button"
        onClick={() => onSort(field)}
        className="inline-flex items-center font-semibold uppercase tracking-wide hover:text-zinc-800"
      >
        {label}
        <SortIndicator active={sortBy === field} sortOrder={sortOrder} />
      </button>
    </th>
  );
}

function PaginationBar({
  page,
  totalPages,
  pageSize,
  onPageChange,
  onPageSizeChange,
}: {
  page: number;
  totalPages: number;
  pageSize: number;
  onPageChange: (page: number) => void;
  onPageSizeChange: (size: number) => void;
}) {
  const pageNumbers = useMemo(() => {
    const maxVisible = 5;
    if (totalPages <= maxVisible) {
      return Array.from({ length: totalPages }, (_, i) => i);
    }

    const start = Math.max(0, Math.min(page - 2, totalPages - maxVisible));
    return Array.from({ length: maxVisible }, (_, i) => start + i);
  }, [page, totalPages]);

  if (totalPages === 0) {
    return null;
  }

  return (
    <div className="mt-4 flex flex-wrap items-center justify-between gap-3 text-sm text-zinc-600">
      <p>
        Page {page + 1} of {totalPages}
      </p>

      <div className="flex flex-wrap items-center gap-2">
        <label className="flex items-center gap-2">
          <span>Rows</span>
          <select
            value={pageSize}
            onChange={(e) => onPageSizeChange(Number(e.target.value))}
            className="h-8 rounded-md border border-zinc-200 bg-white px-2 text-sm"
          >
            {PAGE_SIZES.map((size) => (
              <option key={size} value={size}>
                {size}
              </option>
            ))}
          </select>
        </label>

        <div className="flex items-center gap-1">
          <Button
            variant="secondary"
            className="h-8 px-2"
            disabled={page === 0}
            onClick={() => onPageChange(0)}
          >
            «
          </Button>
          <Button
            variant="secondary"
            className="h-8 px-2"
            disabled={page === 0}
            onClick={() => onPageChange(page - 1)}
          >
            ‹
          </Button>
          {pageNumbers.map((pageNumber) => (
            <button
              key={pageNumber}
              type="button"
              onClick={() => onPageChange(pageNumber)}
              className={`h-8 min-w-8 rounded-md px-2 text-sm ${
                pageNumber === page
                  ? "bg-[#0d2574] text-white"
                  : "border border-zinc-200 bg-white hover:bg-zinc-50"
              }`}
            >
              {pageNumber + 1}
            </button>
          ))}
          <Button
            variant="secondary"
            className="h-8 px-2"
            disabled={page >= totalPages - 1}
            onClick={() => onPageChange(page + 1)}
          >
            ›
          </Button>
          <Button
            variant="secondary"
            className="h-8 px-2"
            disabled={page >= totalPages - 1}
            onClick={() => onPageChange(totalPages - 1)}
          >
            »
          </Button>
        </div>
      </div>
    </div>
  );
}

function UsersTableSection({
  title,
  users,
  searchTerm,
  onSearchTermChange,
  selectedStatuses,
  onSelectedStatusesChange,
  error,
  page,
  pageSize,
  sortBy,
  sortOrder,
  totalPages,
  onSort,
  onPageChange,
  onPageSizeChange,
}: {
  title: string;
  users: UserWithVisitStats[];
  searchTerm: string;
  onSearchTermChange: (value: string) => void;
  selectedStatuses: Set<ActivityStatus>;
  onSelectedStatusesChange: (statuses: Set<ActivityStatus>) => void;
  error: string | null;
  page: number;
  pageSize: number;
  sortBy: string;
  sortOrder: SortOrder;
  totalPages: number;
  onSort: (field: string) => void;
  onPageChange: (page: number) => void;
  onPageSizeChange: (size: number) => void;
}) {
  const filtered = users.filter((user) => {
    if (!matchesStatusSelection(user, selectedStatuses)) {
      return false;
    }

    const keyword = searchTerm.trim().toLowerCase();
    if (!keyword) {
      return true;
    }
    return (
      user.name.toLowerCase().includes(keyword) ||
      user.territory.toLowerCase().includes(keyword) ||
      user.campaign.toLowerCase().includes(keyword)
    );
  });

  return (
    <section className="rounded-md border border-zinc-200 bg-white p-4">
      <h2 className="mb-3 text-lg font-semibold text-zinc-900">{title}</h2>
      <div className="mb-3 flex flex-wrap items-center gap-2">
        <label className="relative flex h-9 w-56 items-center">
          <span className="pointer-events-none absolute left-3">
            <SearchIcon />
          </span>
          <input
            type="search"
            value={searchTerm}
            onChange={(e) => onSearchTermChange(e.target.value)}
            placeholder="Search"
            className="h-full w-full rounded-md border border-zinc-200 bg-white py-0 pl-9 pr-3 text-sm text-zinc-900 placeholder:text-zinc-400"
          />
        </label>
        <div className="ml-auto flex flex-wrap items-center gap-2">
          <Button variant="secondary" className="h-9 rounded-full px-4 py-1">
            Edit
          </Button>
          <Button variant="secondary" className="h-9 rounded-full px-4 py-1">
            New volunteer +
          </Button>
          <StatusFilterDropdown
            selectedStatuses={selectedStatuses}
            onSelectedStatusesChange={onSelectedStatusesChange}
          />
        </div>
      </div>

      <div className="overflow-x-auto">
        <table className="min-w-full text-left text-xs text-zinc-600">
          <thead>
            <tr className="border-b border-zinc-200 bg-zinc-50 text-[11px] uppercase tracking-wide text-zinc-500">
              <th className="px-2 py-2">ID</th>
              <SortableColumnHeader
                label="Name"
                field="name"
                sortBy={sortBy}
                sortOrder={sortOrder}
                onSort={onSort}
              />
              <SortableColumnHeader
                label="Territory"
                field="territory"
                sortBy={sortBy}
                sortOrder={sortOrder}
                onSort={onSort}
              />
              <th className="px-2 py-2">Role</th>
              <th className="px-2 py-2">Status</th>
              <SortableColumnHeader
                label="Doors Knocked"
                field="totalDoorKnocked"
                sortBy={sortBy}
                sortOrder={sortOrder}
                onSort={onSort}
              />
              <th className="px-2 py-2">Last Active</th>
              <SortableColumnHeader
                label="Campaign"
                field="campaign"
                sortBy={sortBy}
                sortOrder={sortOrder}
                onSort={onSort}
              />
            </tr>
          </thead>
          <tbody>
            {filtered.map((user, idx) => (
              <tr key={user.id} className="border-b border-zinc-100">
                <td className="px-2 py-2">{page * pageSize + idx + 1}</td>
                <td className="px-2 py-2 text-zinc-900">{user.name}</td>
                <td className="px-2 py-2">{user.territory}</td>
                <td className="px-2 py-2">{formatRole(user.role)}</td>
                <td className="px-2 py-2">
                  <StatusBadge user={user} />
                </td>
                <td className="px-2 py-2">{user.totalDoorKnocked}</td>
                <td className="px-2 py-2">{formatLastActive(user.lastActive)}</td>
                <td className="px-2 py-2">{user.campaign}</td>
              </tr>
            ))}
            {error && (
              <tr>
                <td colSpan={8} className="px-2 py-6 text-center text-red-500">
                  Failed to load users
                </td>
              </tr>
            )}
            {!error && filtered.length === 0 && (
              <tr>
                <td colSpan={8} className="px-2 py-4 text-center text-zinc-400">
                  No users in this section
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      <PaginationBar
        page={page}
        totalPages={totalPages}
        pageSize={pageSize}
        onPageChange={onPageChange}
        onPageSizeChange={onPageSizeChange}
      />
    </section>
  );
}

export default function UsersPage() {
  const [searchTerm, setSearchTerm] = useState("");
  const [activeRole, setActiveRole] = useState<VolunteerRole>("DOORKNOCKER");
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(20);
  const [sortBy, setSortBy] = useState("name");
  const [sortOrder, setSortOrder] = useState<SortOrder>("asc");
  const [selectedStatuses, setSelectedStatuses] = useState(createDefaultStatusSelection);

  const pageParams = useMemo(
    () => ({
      page,
      size: pageSize,
      role: activeRole,
      sortBy,
      sortOrder,
    }),
    [page, pageSize, activeRole, sortBy, sortOrder],
  );

  const { users, page: pageData, loading, error } = useUsers(pageParams);

  const totalPages = pageData?.totalPages ?? 0;

  function handleRoleChange(role: VolunteerRole) {
    setActiveRole(role);
    setPage(0);
    setSelectedStatuses(createDefaultStatusSelection());
  }

  function handleSort(field: string) {
    if (sortBy === field) {
      setSortOrder((current) => (current === "asc" ? "desc" : "asc"));
    } else {
      setSortBy(field);
      setSortOrder("asc");
    }
    setPage(0);
  }

  function handlePageSizeChange(size: number) {
    setPageSize(size);
    setPage(0);
  }

  return (
    <>
      <div className="mb-6">
        <h1 className="text-4xl font-semibold">Volunteers</h1>
      </div>

      <div className="mb-4 flex items-center gap-2">
        {SECTION_ROLES.map((role) => {
          const active = role === activeRole;
          return (
            <button
              key={role}
              type="button"
              onClick={() => handleRoleChange(role)}
              className={`rounded-full px-4 py-1.5 text-sm font-medium transition ${
                active
                  ? "bg-[#0d2574] text-white"
                  : "border border-zinc-300 bg-white text-zinc-700 hover:bg-zinc-50"
              }`}
            >
              {formatRole(role)}
            </button>
          );
        })}
      </div>

      {loading && <p className="text-zinc-600">Loading volunteers...</p>}

      {!loading && (
        <UsersTableSection
          title={formatRole(activeRole)}
          users={users}
          searchTerm={searchTerm}
          onSearchTermChange={setSearchTerm}
          selectedStatuses={selectedStatuses}
          onSelectedStatusesChange={setSelectedStatuses}
          error={error}
          page={page}
          pageSize={pageSize}
          sortBy={sortBy}
          sortOrder={sortOrder}
          totalPages={totalPages}
          onSort={handleSort}
          onPageChange={setPage}
          onPageSizeChange={handlePageSizeChange}
        />
      )}
    </>
  );
}
