import React from "react";
import { Link } from "react-router-dom";

const CreateProjectButton = () => {
  return (
    <React.Fragment>
      <Link to="/addProject" className="btn btn-lg btn-info">
        <ion-icon name="add-circle" />
        Create a Project
      </Link>
    </React.Fragment>
  );
};

export default CreateProjectButton;
