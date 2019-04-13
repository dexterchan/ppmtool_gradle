import axios from "axios";
import { GET_ERRORS, GET_PROJECTS } from "./types";

//called by AddProject component
//curried functions here!
//caller in the form of createProject(project,history)(dispatch)
export const createProject = (project, history) => async dispatch2 => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
  } catch (err) {
    dispatch2({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

//called by DashBoard component
export const getProjects = (project, history) => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};