export interface DepartmentDTO {
    id: number;
    name: string;
  }
  
  export interface EmployeeDTO {
    id: number;
    name: string;
    position: string;
    salary: number;
    department: DepartmentDTO;
  }
  