"use client";

import { usePathname } from "next/navigation";
import { Header } from "@/components/layout/Header";

export function AppShell({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const isDashboard = pathname.startsWith("/users");

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
