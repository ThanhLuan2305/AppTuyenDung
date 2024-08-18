import { useEffect, useState } from "react";
// import { getAllCodeService } from "../service/userService";
import axios from "axios";
const useFetchDataJobType = () => {
  const [dataJobType, setdataJobType] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
      axios.get("http://localhost:8080/api/get-all-code/job-types").then((responseFeature) => {
        setdataJobType(responseFeature.data);
      })
      };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
};

const useFetchDataJobLevel = () => {
  const [dataJobLevel, setdataJobLevel] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
        axios.get("http://localhost:8080/api/get-all-code/job-levels").then((responseFeature) => {
          setdataJobLevel(responseFeature.data);
        })
        };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
  return { dataJobLevel };
};

const useFetchDataSalaryType = () => {
  const [dataSalaryType, setdataSalaryType] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
        axios.get("http://localhost:8080/api/get-all-code/salary-types").then((responseFeature) => {
          setdataSalaryType(responseFeature.data);
        })
        };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
  return { dataSalaryType };
};

const useFetchDataExpType = () => {
  const [dataExpType, setdataExpType] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
        axios.get("http://localhost:8080/api/get-all-code/exp-types").then((responseFeature) => {
          setdataExpType(responseFeature.data);
        })
        };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
  return { dataExpType };
};

const useFetchDataWorkType = () => {
  const [dataWorkType, setdataWorkType] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
        axios.get("http://localhost:8080/api/get-all-code/work-types").then((responseFeature) => {
          setdataWorkType(responseFeature.data);
        })
        };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
  return { dataWorkType };
};

const useFetchDataJobLocation = () => {
  const [dataJobLocation, setdataJobLocation] = useState([]);
  useEffect(() => {
    try {
      let fetchData = async () => {
        axios.get("http://localhost:8080/api/get-all-code/provinces").then((responseFeature) => {
          setdataJobLocation(responseFeature.data);
        })
        };
      fetchData();
    } catch (error) {
      console.log(error);
    }
  }, []);
  return { dataJobLocation };
};



export { useFetchDataJobType,useFetchDataJobLevel,useFetchDataSalaryType,useFetchDataExpType,useFetchDataWorkType,useFetchDataJobLocation };
