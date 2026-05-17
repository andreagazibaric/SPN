import { useEffect, useState } from "react";
import { getCategories, createCategory, deleteCategory } from "../api/api";

export default function CategoriesPage() {
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    load();
  }, []);

  const load = async () => {
    const data = await getCategories();
    setCategories(data);
  };

  const handleCreate = async () => {
    try {
      await createCategory({ name });
      setName("");
      setError("");
      load();
    } catch (e) {
      setError(e.message);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteCategory(id);
      load();
    } catch (e) {
      setError(e.message);
    }
  };

  return (
    <div className="page">
      <h2>Kategorije</h2>

      {error && <div className="error-box">{error}</div>}

      <div className="form">
        <input
          placeholder="Nova kategorija"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <button onClick={handleCreate}>Dodaj</button>
      </div>

      <ul>
        {categories.map((c) => (
          <li key={c.id} className="row">
            {c.name}

            <button onClick={() => handleDelete(c.id)}>Izbriši</button>
          </li>
        ))}
      </ul>
    </div>
  );
}
