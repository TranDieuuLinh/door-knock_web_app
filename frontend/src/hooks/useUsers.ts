"use client";

import { useCallback, useEffect, useState } from "react";
import { api, ApiError } from "@/lib/api";
import type { PageResponse, UserPageParams, UserWithVisitStats } from "@/types";

const MAX_RETRIES = 8;
const RETRY_DELAY_MS = 1500;

function isRetryableError(err: unknown): boolean {
  return !(err instanceof ApiError);
}

async function sleep(ms: number) {
  await new Promise((resolve) => setTimeout(resolve, ms));
}

function buildRequestKey(params: UserPageParams, refetchCount: number) {
  const { page, size, role, sortBy, sortOrder } = params;
  return `${page}|${size}|${role ?? ""}|${sortBy}|${sortOrder}|${refetchCount}`;
}

async function loadUsers(
  params: UserPageParams,
): Promise<PageResponse<UserWithVisitStats>> {
  for (let attempt = 0; attempt <= MAX_RETRIES; attempt++) {
    try {
      return await api.users.getAllWithVisitStats(params);
    } catch (err) {
      const canRetry = isRetryableError(err) && attempt < MAX_RETRIES;
      if (canRetry) {
        await sleep(RETRY_DELAY_MS);
        continue;
      }

      const message =
        err instanceof ApiError
          ? `Failed to load users (${err.status})`
          : "Failed to load users. Is the backend running on port 8080?";
      throw new Error(message);
    }
  }

  throw new Error("Failed to load users");
}

type FetchResult = {
  key: string;
  pageData: PageResponse<UserWithVisitStats> | null;
  error: string | null;
};

export function useUsers(params: UserPageParams) {
  const { page, size, role, sortBy, sortOrder } = params;
  const [refetchCount, setRefetchCount] = useState(0);
  const [result, setResult] = useState<FetchResult>({
    key: "",
    pageData: null,
    error: null,
  });

  const requestKey = buildRequestKey(params, refetchCount);
  const isCurrent = result.key === requestKey;

  useEffect(() => {
    let cancelled = false;
    const key = requestKey;
    const requestParams = { page, size, role, sortBy, sortOrder };

    loadUsers(requestParams)
      .then((data) => {
        if (!cancelled) {
          setResult({ key, pageData: data, error: null });
        }
      })
      .catch((err: unknown) => {
        if (!cancelled) {
          const message =
            err instanceof Error ? err.message : "Failed to load users";
          setResult({ key, pageData: null, error: message });
        }
      });

    return () => {
      cancelled = true;
    };
  }, [requestKey, page, size, role, sortBy, sortOrder]);

  const refetch = useCallback(() => {
    setRefetchCount((count) => count + 1);
  }, []);

  return {
    users: isCurrent ? (result.pageData?.content ?? []) : [],
    page: isCurrent ? result.pageData : null,
    loading: !isCurrent,
    error: isCurrent ? result.error : null,
    refetch,
  };
}
