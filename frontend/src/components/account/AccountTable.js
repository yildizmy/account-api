import { Table } from "antd";
import React, { useEffect } from "react";
import {
  Box,
  Container,
  createTheme,
  CssBaseline,
  Divider,
  Grid,
  ThemeProvider,
  Typography,
} from "@mui/material";
import useAlert from "../alert/UseAlert";
import AccountService from "../../services/AccountService";
import CreateAccountButton from "./CreateAccountButton";

const theme = createTheme();

export default function AccountTable({ accounts, setAccounts }) {
  const { setAlert } = useAlert();

  useEffect(() => {
    AccountService.getAll()
      .then((accounts) => {
        setAccounts(accounts);
      })
      .catch((error) => {
        setAlert(error.response.data.message, "error");
      });
  }, [setAccounts, setAlert]);

  const columns = [
    {
      title: "Account No",
      dataIndex: "id",
      key: "id",
      responsive: ["sm"],
      width: 120,
    },
    {
      title: "Customer Name",
      dataIndex: "customerName",
      key: "customerName",
      responsive: ["sm"],
    },
    {
      title: "Balance",
      dataIndex: "balance",
      key: "balance",
      responsive: ["sm"],
      align: "right",
    },
  ];

  // data to fill up the rows of the table
  const data = accounts.map((account) => {
    return {
      key: account.id,
      id: account.id,
      balance: account.balance,
      customerName: account.customerName,
    };
  });

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <main>
        <Box
          sx={{
            bgcolor: "background.paper",
            pt: 2,
            pb: 0,
            mb: -4,
          }}
        >
          <Container sx={{ width: 960 }}>
            <Typography
              component="h5"
              variant="h6"
              align="left"
              color="text.primary"
              gutterBottom
              sx={{ ml: "-17" }}
            >
              Accounts
            </Typography>
            <Divider variant="fullWidth" />
          </Container>
        </Box>
        <Container
          sx={{ width: 960, paddingLeft: 0 }}
          style={{ paddingLeft: 0, paddingRight: 0, paddingTop: 50 }}
        >
          <Grid container spacing={0}>
            <Container sx={{ padding: 0 }}>
              <div style={{ display: "flex" }}>
                <div>{<CreateAccountButton />}</div>
              </div>
              <Table
                pagination={{ pageSize: 5 }}
                style={{ paddingTop: 20, margin: 0 }}
                dataSource={data}
                columns={columns}
              />
            </Container>
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
}
