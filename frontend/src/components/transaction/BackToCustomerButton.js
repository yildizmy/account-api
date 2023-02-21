import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";

export default function BackToCustomerButton({ customer }) {
  return (
    <Link to={`/customers/`}>
      <Button type="primary">Back to Customers</Button>
    </Link>
  );
}
