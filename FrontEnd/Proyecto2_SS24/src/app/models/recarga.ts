export class Recarga {

    cantidad: string;
    fechaRecarga: string;
    idUsuario: number;
    idTipoUsuario: number

    constructor(cantidad: string, fechaRecarga: string, idUsuario: number, idTipoUsuario: number) {
        this.cantidad = cantidad;
        this.fechaRecarga = fechaRecarga;
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
    }

}