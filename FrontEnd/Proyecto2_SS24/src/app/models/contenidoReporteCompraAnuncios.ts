export class ContenidoReporteCompraAnuncio {

    costo: number;
    tipoAnuncio: string;
    nombreAnunciante: string;
    periodoTiempo: number;
    titulo: string;
    fechaCompra: string;
    isVigente: boolean;

    constructor(costo: number, tipoAnuncio: string, nombreAnunciante: string,
                periodoTiempo: number, titulo: string, fechaCompra: string,
                isVigente: boolean) {
        this.costo = costo;
        this.tipoAnuncio = tipoAnuncio;
        this.nombreAnunciante = nombreAnunciante;
        this.periodoTiempo = periodoTiempo;
        this.titulo = titulo;
        this.fechaCompra = fechaCompra;
        this.isVigente = isVigente;
    }

}