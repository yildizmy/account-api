import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";

export default function CreateCustomerButton() {
  return (
    <Link to="/customers/create">
      <Button type="primary">Add Customer</Button>
    </Link>
  );
}
