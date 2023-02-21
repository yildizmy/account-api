import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";

export default function ViewButton({ customer }) {
  return (
    <Link to={`/transactions/${customer.id}`}>
      <Button type="primary">Transactions</Button>
    </Link>
  );
}
