import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

//called by AddProject component
//curried functions here!
//caller in the form of createProject(project,history)(dispatch)
export const createProject = (project, history) => async dispatch2 => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
    dispatch2({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (err) {
    dispatch2({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

//called by DashBoard component
export const getProjects = () => async dispatch => {
  const res = await axios.get("http://localhost:8080/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: res.data
  });
};

export const getProject = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`http://localhost:8080/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data
    });
  } catch (ex) {
    history.push("/dashboard");
  }
};

export const deleteProject = id => async dispatch => {
  const res = await axios.delete(`http://localhost:8080/api/project/${id}`);
  dispatch({
    type: DELETE_PROJECT,
    payload: id
  });
};
