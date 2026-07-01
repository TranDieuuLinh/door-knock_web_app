import { config } from "@/lib/config";
import { buildUserPageQuery } from "@/lib/userQuery";
import type {
  CreateUserRequest,
  PageResponse,
  UpdateUserRequest,
  User,
  UserPageParams,
  UserWithVisitStats,
} from "@/types";

class ApiError extends Error {
  constructor(
    public status: number,
    message: string,
  ) {
    super(message);
    this.name = "ApiError";
  }
}

async function request<T>(
  path: string,
  options: RequestInit = {},
): Promise<T> {
  const url = `${config.apiUrl}${path}`;

  const response = await fetch(url, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      ...options.headers,
    },
  });

  if (!response.ok) {
    const message = await response.text().catch(() => response.statusText);
    throw new ApiError(response.status, message || "Request failed");
  }

  if (response.status === 204) {
    return undefined as T;
  }

  return response.json() as Promise<T>;
}

export const api = {
  users: {
    getAll: (params: UserPageParams) =>
      request<PageResponse<User>>(`/api/users?${buildUserPageQuery(params)}`),
    getAllWithVisitStats: (params: UserPageParams) =>
      request<PageResponse<UserWithVisitStats>>(
        `/api/users/volunteers?${buildUserPageQuery(params)}`,
      ),
    getById: (id: string) => request<User>(`/api/users/${id}`),
    create: (body: CreateUserRequest) =>
      request<User>("/api/users", {
        method: "POST",
        body: JSON.stringify(body),
      }),
    update: (id: string, body: UpdateUserRequest) =>
      request<User>(`/api/users/${id}`, {
        method: "PUT",
        body: JSON.stringify(body),
      }),
    delete: (id: string) =>
      request<void>(`/api/users/${id}`, { method: "DELETE" }),
  },
};

export { ApiError };
