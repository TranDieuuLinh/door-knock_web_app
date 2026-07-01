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

    void (async () => {
      for (let attempt = 0; attempt <= MAX_RETRIES; attempt++) {
        try {
          const data = await api.users.getAllWithVisitStats(requestParams);
          if (cancelled) {
            return;
          }
          setResult({ key, pageData: data, error: null });
          return;
        } catch (err) {
          const canRetry = isRetryableError(err) && attempt < MAX_RETRIES;
          if (canRetry) {
            await sleep(RETRY_DELAY_MS);
            continue;
          }

          if (cancelled) {
            return;
          }

          const message =
            err instanceof ApiError
              ? `Failed to load users (${err.status})`
              : "Failed to load users. Is the backend running on port 8080?";
          setResult({ key, pageData: null, error: message });
          return;
        }
      }
    })();

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
