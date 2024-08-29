import axios from "../axios";

const getListPostService = (data) => {
  if (!data?.search) {
    data.search = "";
  }
  if (data.isHot === 1) {
    return axios.get(
      `/public/get-filter-post?limit=${data.limit}&offset=${data.offset}&categoryJobCode=${data.categoryJobCode}&addressCode=${data.addressCode}&salaryJobCode=${data.salaryJobCode}&categoryJoblevelCode=${data.categoryJoblevelCode}&categoryWorktypeCode=${data.categoryWorktypeCode}&experienceJobCode=${data.experienceJobCode}&isHot=${data.isHot}&search=${data.search}`
    );
  }
  return axios.get(
    `/public/get-filter-post?limit=${data.limit}&offset=${data.offset}&categoryJobCode=${data.categoryJobCode}&addressCode=${data.addressCode}&salaryJobCode=${data.salaryJobCode}&categoryJoblevelCode=${data.categoryJoblevelCode}&categoryWorktypeCode=${data.categoryWorktypeCode}&experienceJobCode=${data.experienceJobCode}&search=${data.search}`
  );
};
const getListJobTypeAndCountPost = (data) => {};

//===============ALL CODE========================//
const getAllCodeService = (type) => {
  return axios.get(`/public/get-all-code?type=${type}`);
};

const getDetailPostByIdService = (id) => {
  return axios.get(`/public/get-detail-post-by-id?id=${id}`);
};

const handleLoginService = (data) => {
  return axios.post(`http://localhost:8080/auth/login`, data);
};

const createNewUser = (data) => {
  return axios.post(`http://localhost:8080/auth/register`, data)
}
const getDetailUserById = (data)=>{
  return axios.get(`http://localhost:8080/public/get-users/${data}`)
}

const UpdateUserService = (data) => {
  return axios.put(`/api/update-user`, data)

}

const UpdateUserSettingService = (data) => {
  return axios.put(`/api/setDataUserSetting`, data)

}

const getAllSkillByJobCode = (categoryJobCode) => {
  return axios.get(`/api/get-all-skill-by-job-code?categoryJobCode=${categoryJobCode}`)
}

const getDetailCompanyByUserId = (userId,companyId) => {
  return axios.get(`/public/admin/api/get-detail-company-by-userId?userId=${userId}&companyId=${companyId}`)

}
const createCompanyService = async (formData) => {
  try {
    console.log(formData);
      let response = await axios.post('http://localhost:8080/public/admin/create-company', formData);
      console.log(response)
      return response.data;
  } catch (error) {
      console.error("Error creating company:", error);
      return error.response.data;
  }
}

const updateCompanyService = (data) => {
  return axios.put(`/public/admin/api/update-company`, data)

}

export {
  getListPostService,
  getListJobTypeAndCountPost,
  getAllCodeService,
  getDetailPostByIdService,
  handleLoginService,
  createNewUser,
  UpdateUserService,
  getDetailUserById,
  getAllSkillByJobCode,
  UpdateUserSettingService,
  getDetailCompanyByUserId,
  updateCompanyService,
  createCompanyService

};
