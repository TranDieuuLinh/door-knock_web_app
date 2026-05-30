"use client";

import { useUsers } from "@/hooks/useUsers";
import { Button } from "@/components/ui/Button";

export default function UsersPage() {
  const { users, loading, error, refetch } = useUsers();

  return (
    <div className="mx-auto max-w-5xl px-6 py-12">
      <div className="mb-8 flex items-center justify-between gap-4">
        <div>
          <h1 className="text-3xl font-semibold tracking-tight">Users</h1>
          <p className="mt-2 text-zinc-600 dark:text-zinc-400">
            Fetched from the backend via <code className="text-sm">/api/users</code>.
          </p>
        </div>
        <Button variant="secondary" onClick={() => void refetch()}>
          Refresh
        </Button>
      </div>

      {loading && (
        <p className="text-zinc-600 dark:text-zinc-400">Loading users...</p>
      )}

      {error && (
        <div className="rounded-lg border border-red-200 bg-red-50 px-4 py-3 text-red-800 dark:border-red-900 dark:bg-red-950 dark:text-red-200">
          {error}
        </div>
      )}

      {!loading && !error && users.length === 0 && (
        <p className="text-zinc-600 dark:text-zinc-400">No users yet.</p>
      )}

      {!loading && !error && users.length > 0 && (
        <ul className="divide-y divide-zinc-200 overflow-hidden rounded-xl border border-zinc-200 bg-white dark:divide-zinc-800 dark:border-zinc-800 dark:bg-zinc-900">
          {users.map((user) => (
            <li
              key={user.id}
              className="flex items-center justify-between px-4 py-4"
            >
              <div>
                <p className="font-medium">{user.name}</p>
                <p className="text-sm text-zinc-600 dark:text-zinc-400">
                  {user.email}
                </p>
              </div>
              <span className="text-sm text-zinc-500">#{user.id}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
