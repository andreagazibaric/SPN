import { useEffect, useState } from "react";
import {
  getHabits,
  getCategories,
  createHabit,
  deleteHabit,
  updateHabit,
} from "../api/api";

import LogList from "../components/LogList";

export default function HabitPage() {
  const [habits, setHabits] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selected, setSelected] = useState(null);
  const [showEdit, setShowEdit] = useState(false);

  const [title, setTitle] = useState("");
  const [categoryId, setCategoryId] = useState("");

  useEffect(() => {
    load();
  }, []);

  useEffect(() => {
    if (selected) {
      setTitle(selected.title);
      setCategoryId(selected.category?.id || "");
      setShowEdit(false);
    }
  }, [selected]);

  const load = async () => {
    setHabits(await getHabits());
    setCategories(await getCategories());
  };

  const handleCreate = async () => {
    if (!title || !categoryId) {
      alert("Unesi naziv i kategoriju!");
      return;
    }

    await createHabit({
      title,
      category: { id: categoryId },
    });

    setTitle("");
    setCategoryId("");

    await load();

    setShowEdit(false);
  };

  const handleDelete = async (id) => {
    try {
      await deleteHabit(id);
      load();
    } catch (e) {
      setError(e.message);
    }
  };

  const handleUpdate = async () => {
    await updateHabit(selected.id, {
      title,
      category: { id: categoryId },
    });

    await load();

    const updatedHabit = habits.find((h) => h.id === selected.id);

    if (updatedHabit) {
      setSelected({
        ...updatedHabit,
        title,
        category: categories.find((c) => c.id == categoryId),
      });
    }
    setShowEdit(false);
  };

  return (
    <div className="layout">
      <div className="master">
        {!selected ? (
          <>
            <h2>Popis navika</h2>

            <hr />

            {habits.map((h) => (
              <div
                key={h.id}
                className="habit-row"
                onClick={() => setSelected(h)}
              >
                <span>{h.title}</span>
              </div>
            ))}
          </>
        ) : (
          <div className="habit-master-detail">
            <button className="back-btn" onClick={() => setSelected(null)}>
              ← Povratak
            </button>

            <h2>{selected.title}</h2>

            <div className="category-badge">
              Kategorija: {selected.category?.name}
            </div>

            <div className="habit-info-card">
              {!showEdit ? (
                <button
                  className="edit-btn"
                  onClick={() => {
                    setShowEdit(true);
                  }}
                >
                  Uredi naviku
                </button>
              ) : (
                <>
                  <h2>Spremi, obriši ili napravi novu naviku</h2>

                  <input
                    placeholder="Naziv navike"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                  />

                  <select
                    value={categoryId}
                    onChange={(e) => setCategoryId(e.target.value)}
                  >
                    <option value="">Izaberi kategoriju</option>

                    {categories.map((c) => (
                      <option key={c.id} value={c.id}>
                        {c.name}
                      </option>
                    ))}
                  </select>

                  <div className="edit-actions">
                    <button className="add-btn" onClick={handleUpdate}>
                      Spremi
                    </button>

                    <button
                      className="delete-btn"
                      onClick={async () => {
                        await deleteHabit(selected.id);

                        setSelected(null);
                        setShowEdit(false);

                        load();
                      }}
                    >
                      Izbriši
                    </button>

                    <button className="add-btn" onClick={handleCreate}>
                      Dodaj naviku
                    </button>

                    <button
                      className="cancel-btn"
                      onClick={() => {
                        setShowEdit(false);
                      }}
                    >
                      Odustani
                    </button>
                  </div>
                </>
              )}
            </div>
          </div>
        )}
      </div>

      <div className="detail">
        {!selected ? (
          <div className="empty-state">
            <h2>Odaberi naviku</h2>

            <p>Klikni na naviku iz popisa za prikaz logova i uređivanje.</p>
          </div>
        ) : (
          <>
            <h2>Logovi navike</h2>

            <LogList habit={selected} />
          </>
        )}
      </div>
    </div>
  );
}
