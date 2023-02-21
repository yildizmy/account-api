import React, { useState } from "react";
import "./App.less";
import AppBar from "./components/layout/AppBar";
import CreateCustomer from "./components/customer/CreateCustomer";
import CustomerTable from "./components/customer/CustomerTable";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import TransactionTable from "./components/transaction/TransactionTable";
import CreateAccount from "./components/account/CreateAccount";
import AccountTable from "./components/account/AccountTable";
import AlertPopup from "./components/alert/AlertPopup";

const App = () => {
  const [customers, setCustomers] = useState([]);
  const [accounts, setAccounts] = useState([]);

  return (
    <Router>
      <AppBar></AppBar>
      <AlertPopup />
      <Switch>
        <Route exact path="/customers">
          <CustomerTable customers={customers} setCustomers={setCustomers} />
        </Route>
        <Route exact path="/customers/create">
          <CreateCustomer customers={customers} setCustomers={setCustomers} />
        </Route>
        <Route exact path="/transactions/:id">
          <TransactionTable />
        </Route>
        <Route exact path="/accounts">
          <AccountTable accounts={accounts} setAccounts={setAccounts} />
        </Route>
        <Route exact path="/accounts/create">
          <CreateAccount />
        </Route>
      </Switch>
    </Router>
  );
};

export default App;
