import { useEffect } from "react";
import { useState } from "react";
import { toast } from "react-toastify";
import { getListJobTypeAndCountPost } from "../../service/userService";
import Category from "./Category";
import axios from "axios";
const Categories = () => {
  const [allCategory, setAllCategory] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        axios
          .get(
            `http://localhost:8080/public/get-list-job-count-post?limit=4&offset=0`
          )
          .then((res) => {
            setAllCategory(res.data.content);
          }) //hàm này gọi bên đây
          .catch((err) => {
            toast.error(err);
          }); 
      } catch (error) {
        toast.error("Đã xảy ra lỗi khi lấy dữ liệu");
      }
    };
    fetchData();
  }, []);

  return (
    <>
      <div class="row d-flex justify-contnet-center">
        {allCategory?.map((data, index) => {
          return <Category data={data} key={index} />;
        })}
      </div>
    </>
  );
};

export default Categories;
