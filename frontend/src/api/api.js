const BASE = "http://localhost:8080/api";

// CATEGORIES
export const getCategories = async () => {
  const res = await fetch(`${BASE}/categories`);
  return res.json();
};

export const createCategory = async (cat) => {
  const res = await fetch(`${BASE}/categories`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(cat),
  });
  return res.json();
};

export const deleteCategory = async (id) => {
  const res = await fetch(`${BASE}/categories/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg);
  }

  return res.text();
};

// HABITS
export const getHabits = async () => {
  const res = await fetch(`${BASE}/habits`);
  return res.json();
};

export const createHabit = async (habit) => {
  const res = await fetch(`${BASE}/habits`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(habit),
  });
  return res.json();
};
/*
export const updateHabit = async (id, habit) => {
  const res = await fetch(`${BASE}/habits/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(habit),
  });
  return res.json();
};*/

export const deleteHabit = async (id) => {
  const res = await fetch(`${BASE}/habits/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg);
  }

  return res.text();
};

// LOGS

export const getLogs = async () => {
  const res = await fetch(`${BASE}/logs`);
  return res.json();
};

export const createLog = async (log) => {
  const res = await fetch(`${BASE}/logs`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(log),
  });

  if (!res.ok) {
    const msg = await res.text();
    throw new Error(msg);
  }

  return res.json();
};

export const deleteLog = async (id) => {
  await fetch(`${BASE}/logs/${id}`, {
    method: "DELETE",
  });
};

export async function updateLog(id, data) {
  const res = await fetch(`${BASE}/logs/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!res.ok) {
    throw new Error(await res.text());
  }

  return res.json();
}

export const updateHabit = async (id, habit) => {
  const response = await fetch(`${BASE}/habits/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(habit),
  });

  return response.json();
};
