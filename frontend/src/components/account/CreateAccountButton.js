import { Button } from "antd";
import React from "react";
import { Link } from "react-router-dom";

export default function CreateAccountButton() {
  return (
    <Link to="/accounts/create">
      <Button type="primary">Open Account</Button>
    </Link>
  );
}
