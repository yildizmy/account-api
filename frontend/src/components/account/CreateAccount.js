import { Input, Space, Form, Button } from "antd";
import React from "react";
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

import { useHistory } from "react-router-dom";
import AccountService from "../../services/AccountService";

export default function CreateAccount() {
  const { setAlert } = useAlert();
  const theme = createTheme();
  const history = useHistory();

  const clear = () => {
    document.getElementById("create-account").reset();
  };

  const addAccount = (values) => {
    const accountObject = {
      customerId: values.customerId,
      balance: values.initialAmount,
    };
    AccountService.create(accountObject)
      .then((newAccount) => {
        clear();
        setAlert("Account opened successfully", "success");
      })
      .catch((error) => {
        setAlert(error.response.data.message, "error");
      });
  };

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
              Open Account
            </Typography>
            <Divider variant="fullWidth" />
          </Container>
        </Box>

        <Container
          sx={{ width: 960, paddingLeft: 0 }}
          style={{ paddingLeft: 0, paddingRight: 0, paddingTop: 50 }}
        >
          <Grid container spacing={0}>
            {/* <Container sx={{ width: '100%', padding: 0 }}> */}
            <Container sx={{ padding: 0 }}>
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <Form
                  id="create-account"
                  style={{ padding: 30, width: 600 }}
                  onFinish={(values) => {
                    addAccount(values);
                    // history.goBack();
                  }}
                  autoComplete="off"
                  labelCol={{ span: 8 }}
                >
                  <Form.Item
                    name="customerId"
                    label="Customer Id"
                    rules={[
                      {
                        required: true,
                      },
                      { whitespace: false },
                      ({ getFieldValue }) => ({
                        validator(_, value) {
                          if (value.match(/^[0-9._-]+$/) === null) {
                            return Promise.reject(
                              new Error(
                                "Customer Id should only consists of numbers"
                              )
                            );
                          }
                          return Promise.resolve();
                        },
                      }),
                    ]}
                    hasFeedback
                  >
                    <Input placeholder="Customer Id" />
                  </Form.Item>

                  <Form.Item
                    name="initialAmount"
                    label="Initial Amount"
                    rules={[
                      {
                        required: true,
                      },
                      { whitespace: false },
                      ({ getFieldValue }) => ({
                        validator(_, value) {
                          if (value.match(/^[0-9._-]+$/) === null) {
                            return Promise.reject(
                              new Error(
                                "Initial Amount should only consists of numbers"
                              )
                            );
                          }
                          return Promise.resolve();
                        },
                      }),
                    ]}
                    hasFeedback
                  >
                    <Input placeholder="Initial Amount" />
                  </Form.Item>

                  <Form.Item>
                    <Space wrap style={{ float: "right" }}>
                      <Button type="primary" htmlType="submit">
                        Save
                      </Button>
                      <Button
                        danger
                        htmlType="button"
                        onClick={() => history.goBack()}
                      >
                        Cancel
                      </Button>
                    </Space>
                  </Form.Item>
                </Form>
              </div>
            </Container>
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
}
