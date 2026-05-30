import Link from "next/link";
import { Button } from "@/components/ui/Button";
import { routes } from "@/constants/routes";

export default function Home() {
  return (
    <div className="mx-auto flex max-w-5xl flex-col items-start gap-8 px-6 py-16">
      <div className="space-y-4">
        <p className="text-sm font-medium uppercase tracking-wider text-zinc-500">
          Doorknock
        </p>
        <h1 className="max-w-2xl text-4xl font-semibold tracking-tight">
          Frontend is ready to connect to your Spring Boot API.
        </h1>
        <p className="max-w-xl text-lg leading-8 text-zinc-600 dark:text-zinc-400">
          Next.js, TypeScript, and Tailwind CSS are set up with a shared API
          client, typed models, and reusable components.
        </p>
      </div>

      <div className="flex flex-wrap gap-3">
        <Link href={routes.users}>
          <Button>View users</Button>
        </Link>
        <a
          href="https://nextjs.org/docs"
          target="_blank"
          rel="noopener noreferrer"
        >
          <Button variant="secondary">Next.js docs</Button>
        </a>
      </div>
    </div>
  );
}
