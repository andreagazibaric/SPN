import { useEffect, useState } from "react";
import { getLogs, createLog, deleteLog, updateLog } from "../api/api";

export default function LogList({ habit }) {
  const [logs, setLogs] = useState([]);
  const [date, setDate] = useState("");
  const [completed, setCompleted] = useState(false);
  const [error, setError] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [editForm, setEditForm] = useState({
    date: "",
    completed: false,
  });
  const [showCreateForm, setShowCreateForm] = useState(false);

  useEffect(() => {
    loadLogs();
  }, [habit]);

  const loadLogs = async () => {
    const data = await getLogs();

    const filtered = data
      .filter((l) => l.habit?.id === habit.id)
      .sort((a, b) => new Date(b.date) - new Date(a.date));

    setLogs(filtered);
  };

  const handleCreate = async () => {
    try {
      await createLog({
        date,
        completed,
        habit: { id: habit.id },
      });

      setDate("");
      setCompleted(false);
      setError("");
      loadLogs();
    } catch (err) {
      setError(err.message);
    }
  };

  const handleDelete = async (id) => {
    await deleteLog(id);
    loadLogs();
  };

  const handleEditClick = (log) => {
    setEditingId(log.id);
    setEditForm({
      date: log.date,
      completed: log.completed,
    });
  };

  const cancelEdit = () => {
    setEditingId(null);
    setEditForm({ date: "", completed: false });
  };

  const handleSave = async (id) => {
    await updateLog(id, {
      date: editForm.date,
      completed: editForm.completed,
      habit: { id: habit.id },
    });

    setEditingId(null);
    loadLogs();
  };

  return (
    <div>
      {error && <div className="error-box">{error}</div>}
      <button
        className="add-log-btn"
        onClick={() => setShowCreateForm(!showCreateForm)}
      >
        {showCreateForm ? "Zatvori" : "Dodaj novi zapis"}
      </button>

      {showCreateForm && (
        <div className="form-inline">
          <input
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          />

          <label className="checkbox-label">
            <input
              type="checkbox"
              checked={completed}
              onChange={(e) => setCompleted(e.target.checked)}
            />
            Completed
          </label>

          <button className="save-btn" onClick={handleCreate}>
            Spremi
          </button>
        </div>
      )}
      <div>
        {logs.map((l) => (
          <div key={l.id} className="log-row">
            {editingId === l.id ? (
              <>
                <input
                  type="date"
                  value={editForm.date}
                  onChange={(e) =>
                    setEditForm({ ...editForm, date: e.target.value })
                  }
                />

                <input
                  type="checkbox"
                  checked={editForm.completed}
                  onChange={(e) =>
                    setEditForm({ ...editForm, completed: e.target.checked })
                  }
                />

                <button className="save-btn" onClick={() => handleSave(l.id)}>
                  Spremi
                </button>

                <button className="delete-btn" onClick={cancelEdit}>
                  Odustani
                </button>
              </>
            ) : (
              <>
                <span>{l.date}</span>
                <span>{l.completed ? "✔" : "✖"}</span>

                <button
                  className="change-btn"
                  onClick={() => handleEditClick(l)}
                >
                  Uredi
                </button>

                <button
                  className="delete-btn"
                  onClick={() => handleDelete(l.id)}
                >
                  Izbriši
                </button>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}
