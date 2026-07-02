export const routes = {
  home: "/",
  runsheet: "/runsheet",
  households: "/households",
  users: "/users",
  map: "/map",
  ranking: "/ranking",
  analyse: "/analyse",
} as const;

export const dashboardRoutes = [
  routes.runsheet,
  routes.households,
  routes.users,
  routes.map,
  routes.ranking,
  routes.analyse,
] as const;

export function isDashboardPath(pathname: string) {
  return dashboardRoutes.some(
    (route) => pathname === route || pathname.startsWith(`${route}/`),
  );
}
