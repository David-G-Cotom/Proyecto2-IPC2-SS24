export class Suscripcion {
    
    idUsuario: number;
    fecha: string;
    idRevista: number;

    constructor(idUsuairo: number, fecha: string, idRevista: number) {
        this.idUsuario = idUsuairo;
        this.fecha = fecha;
        this.idRevista = idRevista;
    }

}