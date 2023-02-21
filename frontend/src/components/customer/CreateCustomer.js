import { Input, Space, Form, Button } from "antd";
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
import CustomerService from "../../services/CustomerService";

export default function CreateCustomer() {
  const theme = createTheme();
  const { setAlert } = useAlert();
  const history = useHistory();

  const clear = () => {
    document.getElementById("create-customer").reset();
  };

  const addCustomer = (values) => {
    const customerObject = {
      name: values.name,
      surname: values.surname,
      email: values.email,
    };
    CustomerService.create(customerObject)
      .then((newCustomer) => {
        clear();
        setAlert("Customer created successfully", "success");
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
              Create Customer
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
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center",
                }}
              >
                <Form
                  id="create-customer"
                  style={{ padding: 30, width: 600 }}
                  onFinish={(values) => {
                    addCustomer(values);
                  }}
                  autoComplete="off"
                  labelCol={{ span: 8 }}
                >
                  <Form.Item
                    name="name"
                    label="Name"
                    rules={[
                      {
                        required: true,
                      },
                      { whitespace: true },
                    ]}
                    hasFeedback
                  >
                    <Input placeholder="Name" />
                  </Form.Item>

                  <Form.Item
                    name="surname"
                    label="Surname"
                    rules={[
                      {
                        required: true,
                      },
                      { whitespace: true },
                    ]}
                    hasFeedback
                  >
                    <Input placeholder="Surname" />
                  </Form.Item>

                  <Form.Item
                    name="email"
                    label="email"
                    rules={[
                      {
                        required: true,
                      },
                      { whitespace: true },
                    ]}
                    hasFeedback
                  >
                    <Input placeholder="email" />
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
