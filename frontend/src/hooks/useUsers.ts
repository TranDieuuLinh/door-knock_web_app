"use client";

import { useCallback, useEffect, useState } from "react";
import { api, ApiError } from "@/lib/api";
import type { User } from "@/types";

const MAX_RETRIES = 8;
const RETRY_DELAY_MS = 1500;

function isRetryableError(err: unknown): boolean {
  return !(err instanceof ApiError);
}

async function sleep(ms: number) {
  await new Promise((resolve) => setTimeout(resolve, ms));
}

export function useUsers() {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const fetchUsers = useCallback(async () => {
    setLoading(true);
    setError(null);

    for (let attempt = 0; attempt <= MAX_RETRIES; attempt++) {
      try {
        const data = await api.users.getAll();
        setUsers(data);
        setLoading(false);
        return;
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
        setError(message);
        setLoading(false);
        return;
      }
    }
  }, []);

  useEffect(() => {
    void fetchUsers();
  }, [fetchUsers]);

  return { users, loading, error, refetch: fetchUsers };
}
