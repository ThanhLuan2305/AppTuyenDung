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

const getListCompany = (data) => {
  return axios.get(`/public/get-list-company?limit=${data.limit}&offset=${data.offset}&search=${data.search}`)

}

const handleLoginService = (data) => {
  return axios.post(`http://localhost:8080/auth/login`, data);
};

const createNewUser = (data) => {
  return axios.post(`http://localhost:8080/auth/register`, data);
};
const getDetailUserById = (data) => {
  return axios.get(`http://localhost:8080/public/get-users/${data}`);
};

const UpdateUserService = (data) => {
  return axios.put(`/public/update`, data);
};

const UpdateUserSettingService = (data) => {
  return axios.put(`/public/set-user-setting`, data);
};

const getAllSkillByJobCode = (categoryJobCode) => {
  return axios.get(
    `/public/skill/get-all-skill-by-job-code?categoryJobCode=${categoryJobCode}`
  );
};

const getDetailCompanyByUserId = (userId, companyId) => {
  return axios.get(
    `/public/admin/api/get-detail-company-by-userId?userId=${userId}&companyId=${companyId}`
  );
};
const createCompanyService = async (formData) => {
  try {
    console.log(formData);
    let response = await axios.post(
      "http://localhost:8080/public/admin/create-company",
      formData
    );
    console.log(response);
    return response.data;
  } catch (error) {
    console.error("Error creating company:", error);
    return error.response.data;
  }
};

const updateCompanyService = (data) => {
  return axios.put(`/public/admin/api/update-company`, data);
};

const checkUserPhoneService = (phonenumber) => {
  return axios.get(`/public/check-phonenumber-user?phonenumber=${phonenumber}`);
};
const changePasswordByphone = (data) => {
  return axios.post(`/public/change-password`, data);
};
const handleChangePassword = (data) => {
  return axios.post(`/api/changepassword`, data);
};

const getAllUsers = (data) => {
  return axios.get(
    `/public/get-all-user?limit=${data.limit}&offset=${data.offset}&search=${data.search}`
  );
};

const createJobType = (data) => {
  return axios.post(`/public/create-jobType`, data);
};
const getAllJobType = (data) => {
  return axios.get(
    `/public/get-all-jobtype?limit=${data.limit}&offset=${data.offset}&search=${data.search}`
  );
};

const DeleteJobtype = (codeId) => {
  return axios.delete(`/public/delete-jobtype?code=${codeId}`);
};
const UpdateJobtype = (data) => {
  return axios.put(`/public/update-jobtype`, data);
};
const getDetailJobTypeByCode = (code) => {
  return axios.get(`/public/get-detail-JobType-by-code?code=${code}`);
};

const BanUserService = (userId) => {
  return axios.post(`/api/ban-user`, {
    data: {
      id: userId,
    },
  });
};

const UnbanUserService = (userId) => {
  return axios.post(`/api/unban-user`, {
    data: {
      id: userId,
    },
  });
};

const AddNewUser = (data) => {
  return axios.post(`/api/create-new-user`, data);
};

export {
  getListCompany,
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
  checkUserPhoneService,
  changePasswordByphone,
  createCompanyService,
  handleChangePassword,
  getAllUsers,
  BanUserService,
  UnbanUserService,
  AddNewUser,
  createJobType,
  getAllJobType,
  DeleteJobtype,
  UpdateJobtype,
  getDetailJobTypeByCode,
};
