import { routes } from "@/constants/routes";

export const NAV_ICONS = {
  RunSheetIcon:
    "https://img.icons8.com/?size=100&id=NKRM4VSa2hw2&format=png&color=FFFFFF",
  HouseholdIcon:
    "https://img.icons8.com/?size=100&id=6dL6OzXlCkqV&format=png&color=FFFFFF",
  VolunteerIcon:
    "https://img.icons8.com/?size=100&id=107655&format=png&color=FFFFFF",
  MapIcon: "https://img.icons8.com/?size=100&id=345&format=png&color=FFFFFF",
  RankingIcon:
    "https://img.icons8.com/?size=100&id=6yiQUAER3NXc&format=png&color=FFFFFF",
  AnalyseIcon:
    "https://img.icons8.com/?size=100&id=7Zqkr6Ylo5gh&format=png&color=FFFFFF",
} as const;

export const NAV_ITEMS = [
  { href: routes.runsheet, label: "Runsheet", icon: NAV_ICONS.RunSheetIcon },
  { href: routes.households, label: "Households", icon: NAV_ICONS.HouseholdIcon },
  { href: routes.users, label: "Volunteers", icon: NAV_ICONS.VolunteerIcon },
  { href: routes.map, label: "Map", icon: NAV_ICONS.MapIcon },
  { href: routes.ranking, label: "Ranking", icon: NAV_ICONS.RankingIcon },
  { href: routes.analyse, label: "Analyse", icon: NAV_ICONS.AnalyseIcon },
] as const;
