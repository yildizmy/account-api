import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import { Button, Container } from "@mui/material";
import { Link } from "react-router-dom";

export default function ResponsiveAppBar() {
  const pages = ["customers", "accounts"];

  return (
    <AppBar position="static" style={{ background: "#2E3B55" }}>
      <Container maxWidth="xl" sx={{ width: 960 }}>
        <Toolbar disableGutters>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            {pages.map((page) => (
              <Link
                key={page}
                to={"/" + page.replace(/\s/g, "")}
                style={{
                  cursor: "pointer",
                  textDecoration: "none",
                  paddingRight: "15px",
                }}
              >
                <Button
                  key={page}
                  sx={{
                    my: 2,
                    color: "white",
                    display: "block",
                    paddingLeft: 0,
                  }}
                  type="button"
                >
                  {page}
                </Button>
              </Link>
            ))}
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
}
