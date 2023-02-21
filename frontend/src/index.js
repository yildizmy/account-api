import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import { AlertProvider } from "./components/alert/AlertContext";

ReactDOM.render(
  <AlertProvider>
    <App />
  </AlertProvider>,
  document.getElementById("root")
);
