export class ReporteRevistasTop {

    fechaInicio: string;
    fechaFin: string;
    idRevista: string;
    idUsuario: number;

    constructor(fechaInicio: string, fechaFin: string, idRevista: string, idUsuario: number) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idRevista = idRevista;
        this.idUsuario = idUsuario;
    }

}