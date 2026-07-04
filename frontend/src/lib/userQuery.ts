import type { UserPageParams } from "@/types";

export function buildUserPageQuery(params: UserPageParams): string {
  const search = new URLSearchParams();
  search.set("page", String(params.page));
  search.set("size", String(params.size));
  search.set("sortBy", params.sortBy);
  search.set("sortOrder", params.sortOrder);
  if (params.role) {
    search.set("role", params.role);
  }
  return search.toString();
}
