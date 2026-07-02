import { DashboardSidebar } from "@/components/layout/DashboardSidebar";

export default function DashboardLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="flex h-full min-h-0 w-full bg-zinc-100 text-zinc-900">
      <DashboardSidebar />
      <div className="flex min-h-0 flex-1 flex-col overflow-y-auto p-8">
        {children}
      </div>
    </div>
  );
}
