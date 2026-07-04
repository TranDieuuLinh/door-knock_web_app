export const routes = {
  home: "/",
  runsheet: "/runsheet",
  households: "/households",
  users: "/users",
  map: "/map",
  ranking: "/ranking",
  analyse: "/analyse",
} as const;

export const dashboardRoutes = [routes.users] as const;

export function isDashboardPath(pathname: string) {
  return dashboardRoutes.some(
    (route) => pathname === route || pathname.startsWith(`${route}/`),
  );
}
