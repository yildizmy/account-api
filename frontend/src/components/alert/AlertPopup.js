import { Alert } from "@mui/material";
import useAlert from "./UseAlert";

const AlertPopup = () => {
  const { text, type } = useAlert();

  if (text && type) {
    return (
      <Alert
        variant="filled"
        severity={type}
        sx={{
          position: "absolute",
          left: "50%",
          marginLeft: "-17px",
          bottom: 0,
          zIndex: 999,
        }}
      >
        {text}
      </Alert>
    );
  } else {
    return <></>;
  }
};

export default AlertPopup;
