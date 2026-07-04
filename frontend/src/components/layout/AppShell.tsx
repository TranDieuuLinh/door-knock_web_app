"use client";

import { usePathname } from "next/navigation";
import { Header } from "@/components/layout/Header";
import { isDashboardPath } from "@/constants/routes";

export function AppShell({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const isDashboard = isDashboardPath(pathname);

  if (isDashboard) {
    return <main className="h-screen overflow-hidden">{children}</main>;
  }

  return (
    <>
      <Header />
      <main className="flex-1">{children}</main>
    </>
  );
}
