import React, { Component } from "react";
import PropTypes from "prop-types";
import ProjectItem from "./Project/ProjectItem";
import CreateProjectButton from "./Project/CreateProjectButton";

import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";

class Dashboard extends Component {
  constructor() {
    super();
  }

  //Life Cycle hooks
  componentDidMount(nextProps) {
    console.log("Run componentDidMount");
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateProjectButton />
              <br />
              <hr />
              {projects.map(projectObject => (
                <ProjectItem key={projectObject.id} project={projectObject} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  getProjects: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => {
  console.log("Run mapStateToProps");
  return {
    project: state.project
  };
};

export default connect(
  mapStateToProps,
  { getProjects }
)(Dashboard);
