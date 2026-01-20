import React from 'react';
import "./CategoryList.css";
import { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';

const CategoryList=()=>{
    const {categories}=useContext(AppContext);
    return(
        <div className="category-list-container" style={{height: '100vh', overflowY: 'auto', overFlowX:'hidden'}}>
            <div className="row pe-2">
                search bar
            </div>
            <div className="row g-3 pe-2">
               {categories.map((category,index)=>(
                  <div key={index} className="col12"> 
                      <div  className="card p-3" style={{backgroundColor: category.bgColor}}>
                          <div className="d-flex align-items-center">
                               <div style={{marginRight:'15px'}}>
                                <img src={category.imgUrl} alt={category.name} className="category-image"/>
                               </div>
                               <div className="flex-grow-1">
                                <h5 className="mb-1 text-white">{category.name}</h5>
                                <p className="mb-0 text-white">5 items</p>
                               </div>
                               <div>
                                <button className="btn btn-danger btn-sm">
                                    <i className="bi bi-trash"></i>
                                </button>
                               </div>
                           </div>
                      </div>
                  </div>
                ))}
            </div>

        </div>
    )
}
export default CategoryList;