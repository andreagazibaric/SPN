import LogList from "./LogList";

export default function HabitDetails({ habit }) {
  return (
    <div>
      <h2>{habit.title}</h2>

      <p className="muted">{habit.description}</p>

      <div className="badge">Category: {habit.category?.name}</div>

      <hr />

      <h3>Logs</h3>
      <LogList habit={habit} />
    </div>
  );
}
