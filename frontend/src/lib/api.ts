import { config } from "@/lib/config";
import type {
  CreateUserRequest,
  UpdateUserRequest,
  User,
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
    getAll: () => request<User[]>("/api/users"),
    getById: (id: number) => request<User>(`/api/users/${id}`),
    create: (body: CreateUserRequest) =>
      request<User>("/api/users", {
        method: "POST",
        body: JSON.stringify(body),
      }),
    update: (id: number, body: UpdateUserRequest) =>
      request<User>(`/api/users/${id}`, {
        method: "PUT",
        body: JSON.stringify(body),
      }),
    delete: (id: number) =>
      request<void>(`/api/users/${id}`, { method: "DELETE" }),
  },
};

export { ApiError };
