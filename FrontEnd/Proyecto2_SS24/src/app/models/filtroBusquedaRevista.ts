export class FiltroBusquedaRevista {

    etiquetas: string[];
    categorias: string[];
    idUsuario: number;

    constructor(etiquetas: string[], categorias: string[], idUsuario: number) {
        this.etiquetas = etiquetas;
        this.categorias = categorias;
        this.idUsuario = idUsuario;
    }

}