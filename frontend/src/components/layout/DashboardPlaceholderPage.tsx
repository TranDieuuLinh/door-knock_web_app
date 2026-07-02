export function DashboardPlaceholderPage({ title }: { title: string }) {
  return (
    <div>
      <h1 className="text-4xl font-semibold">{title}</h1>
      <p className="mt-4 text-zinc-600">This page is coming soon.</p>
    </div>
  );
}
