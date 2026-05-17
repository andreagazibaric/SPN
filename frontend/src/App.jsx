import HabitsPage from "./pages/HabitsPage";
import CategoriesPage from "./pages/CategoriesPage";
import { useState } from "react";

export default function App() {
  const [page, setPage] = useState("habits");

  return (
    <div>
      <nav style={{ padding: "10px", gap: "10px" }}>
        <button onClick={() => setPage("habits")}>Popis navika</button>
        <button onClick={() => setPage("categories")}>Kategorije</button>
      </nav>

      {page === "habits" && <HabitsPage />}
      {page === "categories" && <CategoriesPage />}
    </div>
  );
}

/*
import HabitPage from "./pages/HabitsPage";
import CategoriesPage from "./pages/CategoriesPage";

export default function App() {
  return (
    <div>
      <HabitPage />
      {/* <CategoriesPage /> */ /*}
    </div>
  );
}*/
