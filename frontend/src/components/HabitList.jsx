import { deleteHabit } from "../api/api";

export default function HabitList({
  habits,
  onSelect,
  selectedHabit,
  onRefresh,
}) {
  const handleDelete = async (id) => {
    await deleteHabit(id);
    onRefresh();
  };

  return (
    <div>
      {habits.map((h) => (
        <div
          key={h.id}
          className={`habit-row ${selectedHabit?.id === h.id ? "active" : ""}`}
          onClick={() => onSelect(h)}
        >
          <div className="habit-title">{h.title}</div>

          <button
            className="delete-btn"
            onClick={(e) => {
              e.stopPropagation();
              handleDelete(h.id);
            }}
          >
            Delete
          </button>
        </div>
      ))}
    </div>
  );
}
