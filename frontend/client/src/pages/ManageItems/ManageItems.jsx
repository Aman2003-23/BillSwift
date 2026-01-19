import React from 'react';
import './ManageItems.css'
import ItemList from '../../components/ItemList/ItemList';
import ItemForm from '../../components/ItemForm/ItemForm';
const ManageItems=()=>{
    return(
        <div className="items-container text-light">
        <div className="left-column">
           <ItemForm/>
        </div>
        <div className="right-column">
           <ItemList/>
        </div>
       </div>
    )
}
export default ManageItems;