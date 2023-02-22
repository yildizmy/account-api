import { Table } from "antd";
import React, { useEffect, useState } from "react";
import TransactionsService from "../../services/TransactionService";
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
import { useParams } from "react-router-dom";
import BackToCustomerButton from "./BackToCustomerButton";
import useAlert from "../alert/UseAlert";

export default function TransactionTable(props) {
  const theme = createTheme();
  const { setAlert } = useAlert();
  const { id } = useParams();
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    TransactionsService.getAllByCustomerId(id)
      .then((transactions) => {
        setTransactions(transactions);
      })
      .catch((error) => {
        setAlert(error.response.data.message, "error");
      });
  }, [setTransactions]);

  const columns = [
    {
      title: "Description",
      dataIndex: "description",
      key: "description",
      responsive: ["sm"],
    },
    {
      title: "Date",
      dataIndex: "date",
      key: "date",
      responsive: ["sm"],
    },
    {
      title: "Amount",
      dataIndex: "amount",
      key: "amount",
      responsive: ["sm"],
      align: "right",
    },
  ];

  // data to fill up the rows of the table
  const data = transactions.map((transaction) => {
    return {
      key: transaction.id,
      description: transaction.description,
      date: transaction.date,
      amount: transaction.amount,
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
              Transactions
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
                <div>{<BackToCustomerButton />}</div>
              </div>
              <Table
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
