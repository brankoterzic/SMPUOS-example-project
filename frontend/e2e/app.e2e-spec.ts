import { MicroserviceFrontendPage } from './app.po';

describe('microservice-frontend App', function() {
  let page: MicroserviceFrontendPage;

  beforeEach(() => {
    page = new MicroserviceFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
