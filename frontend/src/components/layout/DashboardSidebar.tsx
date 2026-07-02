"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import doorKnockIcon from "../../../assets/logos/door_knock_icon.png";
import { routes } from "@/constants/routes";

const NAV_ICONS = {
  "RunSheetIcon": "https://img.icons8.com/?size=100&id=NKRM4VSa2hw2&format=png&color=FFFFFF",
  "HouseholdIcon": "https://img.icons8.com/?size=100&id=6dL6OzXlCkqV&format=png&color=FFFFFF",
  "VolunteerIcon": "https://img.icons8.com/?size=100&id=107655&format=png&color=FFFFFF",
  "MapIcon": "https://img.icons8.com/?size=100&id=345&format=png&color=FFFFFF",
  "RankingIcon": "https://img.icons8.com/?size=100&id=6yiQUAER3NXc&format=png&color=FFFFFF",
  "AnalyseIcon": "https://img.icons8.com/?size=100&id=7Zqkr6Ylo5gh&format=png&color=FFFFFF",
};

const NAV_ITEMS = [
  { href: routes.runsheet, label: "Runsheet", icon: NAV_ICONS["RunSheetIcon"] },
  { href: routes.households, label: "Households", icon: NAV_ICONS["HouseholdIcon"] },
  { href: routes.users, label: "Volunteers", icon: NAV_ICONS["VolunteerIcon"] },
  { href: routes.map, label: "Map", icon: NAV_ICONS["MapIcon"] },
  { href: routes.ranking, label: "Ranking", icon: NAV_ICONS["RankingIcon"] },
  { href: routes.analyse, label: "Analyse", icon: NAV_ICONS["AnalyseIcon"] },
] as const;

function BrandLogo() {
  return (
    <div className="relative h-9 w-9 shrink-0">
      <Image
        src={doorKnockIcon}
        alt="Doorknock"
        fill
        className="object-contain"
        sizes="36px"
        priority
      />
    </div>
  );
}

function isActivePath(pathname: string, href: string) {
  return pathname === href || pathname.startsWith(`${href}/`);
}

export function DashboardSidebar() {
  const pathname = usePathname();

  return (
    <aside className="flex w-56 shrink-0 flex-col bg-[#0d2574] px-5 py-6 text-white">
      <div className="mb-8 flex items-center justify-center gap-2">
        <BrandLogo />
        <span className="whitespace-nowrap text-3xl font-semibold leading-none tracking-tight">
          Doorknock
        </span>
      </div>

      <nav className="space-y-2 text-sm">
        {NAV_ITEMS.map((item) => {
          const active = isActivePath(pathname, item.href);
          return (
            <Link
              key={item.href}
              href={item.href}
              className={`flex items-center gap-2 rounded px-3 py-2 transition ${
                active
                  ? "bg-white/20 font-semibold text-white"
                  : "text-white/90 hover:bg-white/10 hover:text-white"
              }`}
            >
              <Image src={item.icon} alt={item.label} width={20} height={20} />
              {item.label}
            </Link>
          );
        })}
      </nav>
    
      <div className="mt-auto text-sm text-white/80">
        <div className="mb-1 flex items-center gap-2">
          <span className="inline-flex h-6 w-6 items-center justify-center rounded-full bg-white/95 text-[#0d2574]">
            <svg viewBox="0 0 24 24" className="h-4 w-4 fill-current" aria-hidden>
              <path d="M12 12a4 4 0 1 0-4-4 4 4 0 0 0 4 4Zm0 2c-4 0-7 2-7 4v1h14v-1c0-2-3-4-7-4Z" />
            </svg>
          </span>
          <p className="font-semibold text-white">Anthony</p>
        </div>
        <p>Admin</p>
        <div className="mt-4 flex items-center gap-2">
          <span className="inline-flex h-4 w-4 items-center justify-center text-white">
            <svg viewBox="0 0 24 24" className="h-4 w-4 fill-current" aria-hidden>
              <path d="M19.14 12.94a7.55 7.55 0 0 0 .05-.94 7.55 7.55 0 0 0-.05-.94l2.03-1.58a.5.5 0 0 0 .12-.64l-1.92-3.32a.5.5 0 0 0-.6-.22l-2.39.96a7.28 7.28 0 0 0-1.63-.94l-.36-2.54A.5.5 0 0 0 13.9 2h-3.8a.5.5 0 0 0-.49.42l-.36 2.54a7.28 7.28 0 0 0-1.63.94l-2.39-.96a.5.5 0 0 0-.6.22L2.71 8.48a.5.5 0 0 0 .12.64l2.03 1.58a7.55 7.55 0 0 0-.05.94 7.55 7.55 0 0 0 .05.94L2.83 14.2a.5.5 0 0 0-.12.64l1.92 3.32a.5.5 0 0 0 .6.22l2.39-.96c.5.39 1.04.71 1.63.94l.36 2.54a.5.5 0 0 0 .49.42h3.8a.5.5 0 0 0 .49-.42l.36-2.54c.59-.23 1.13-.55 1.63-.94l2.39.96a.5.5 0 0 0 .6-.22l1.92-3.32a.5.5 0 0 0-.12-.64ZM12 15.5A3.5 3.5 0 1 1 15.5 12 3.5 3.5 0 0 1 12 15.5Z" />
            </svg>
          </span>
          <p>Settings</p>
        </div>
        <p className="mt-1 text-red-300">Log Out</p>
      </div>
    </aside>
  );
}
