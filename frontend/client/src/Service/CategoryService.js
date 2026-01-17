//from here calls will be made to the backedn servers
import axios from 'axios';

export const addCategory=async(Category)=>{
   return await axios.post("http://localhost:8080/api/v1.0/categories",Category)
}

export const deleteCategory=async(categoryId)=>{
   return await axios.delete(`http://localhost:8080/api/v1.0/categories/${categoryId}`);
} 

export const fetchCategories=async()=>{
    return await axios.get("http://localhost:8080/api/v1.0/categories")
}