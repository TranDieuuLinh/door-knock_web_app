export type VolunteerRole = "DOORKNOCKER" | "COORDINATOR" | "ADMIN";

export interface User {
  id: string;
  name: string;
  territory: string;
  campaign: string;
  role: VolunteerRole;
}

export interface UserWithVisitStats extends User {
  totalDoorKnocked: number;
  lastActive: string | null;
}

export interface PageResponse<T> {
  content: T[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export type SortOrder = "asc" | "desc";

export interface UserPageParams {
  page: number;
  size: number;
  role?: VolunteerRole | null;
  sortBy: string;
  sortOrder: SortOrder;
}

export interface CreateUserRequest {
  name: string;
  email: string;
}

export interface UpdateUserRequest {
  name: string;
  email: string;
}
