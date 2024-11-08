export class ReporteCompraAnuncio {

    fechaInicio: string;
    fechaFin: string;
    tipoAnuncio: string;
    periodoTiempo: string;

    constructor(fechaInicio: string, fechaFin: string, tipoAnuncio: string,
                periodoTiempo: string) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoAnuncio = tipoAnuncio;
        this.periodoTiempo = periodoTiempo;
    }

}