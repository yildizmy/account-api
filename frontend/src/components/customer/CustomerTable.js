import { Table, Space } from "antd";
import React, { useEffect } from "react";
import CreateCustomerButton from "./CreateCustomerButton";
import ViewButton from "./ViewButton";
import CustomersService from "../../services/CustomerService";
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

export default function CustomerTable({ customers, setCustomers }) {
  const theme = createTheme();
  const { setAlert } = useAlert();

  useEffect(() => {
    CustomersService.getAll()
      .then((customers) => {
        setCustomers(customers);
      })
      .catch((error) => {
        setAlert(error.response.data.message, "error");
      });
  }, [setCustomers]);

  const columns = [
    {
      title: "Customer Id",
      dataIndex: "id",
      key: "id",
      responsive: ["sm"],
      width: 120
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      responsive: ["sm"]
    },
    {
      title: "Surname",
      dataIndex: "surname",
      key: "surname",
      responsive: ["sm"]
    },
    {
      title: "email",
      dataIndex: "email",
      key: "email",
      responsive: ["sm"]
    },
    {
      title: "Balance",
      dataIndex: "balance",
      key: "balance",
      responsive: ["sm"],
      align: "right",
      width: 150
    },
    {
      title: "Actions",
      dataIndex: "actions",
      key: "id",
      width: 100,
      render: (text, record, index) => (
        <Space align="right">{<ViewButton customer={record} />}</Space>
      ),
    },
  ];

  // data to fill up the rows of the table
  const data = customers.map((customer) => {
    return {
      key: customer.id,
      id: customer.id,
      name: customer.name,
      surname: customer.surname,
      email: customer.email,
      balance: customer.balance,
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
              Customers
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
                <div>{<CreateCustomerButton />}</div>
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
